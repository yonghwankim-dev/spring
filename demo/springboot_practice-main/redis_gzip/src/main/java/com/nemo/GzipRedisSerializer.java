package com.nemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

class GzipRedisSerializer<T> implements RedisSerializer<T> {

	private final ObjectMapper objectMapper;
	private final TypeReference<T> typeRef;
	private final int minCompressionSize;
	private final int bufferSize;

	private static final byte[] GZIP_MAGIC_BYTES = new byte[] {
		(byte)(GZIPInputStream.GZIP_MAGIC & 0xFF),
		(byte)((GZIPInputStream.GZIP_MAGIC >> 8) & 0xFF)
	};

	public GzipRedisSerializer(ObjectMapper objectMapper, TypeReference<T> typeRef) {
		this(objectMapper, typeRef, 1024, 4096);
	}

	public GzipRedisSerializer(ObjectMapper objectMapper, TypeReference<T> typeRef, int minCompressionSize,
		int bufferSize) {
		this.objectMapper = objectMapper;
		this.typeRef = typeRef;
		this.minCompressionSize = minCompressionSize;
		this.bufferSize = bufferSize;
	}

	@Override
	public byte[] serialize(T t) {
		if (t == null) {
			return null;
		}

		try {
			if (minCompressionSize == -1) {
				return encodeGzip(t);
			}

			byte[] bytes = objectMapper.writeValueAsBytes(t);
			if (bytes.length <= minCompressionSize) {
				return bytes;
			}
			return encodeGzip(bytes);
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't serialize object", e);
		}
	}

	private byte[] encodeGzip(byte[] original) {
		try (
			ByteArrayOutputStream bos = new ByteArrayOutputStream(bufferSize);
			GZIPOutputStream gos = new GZIPOutputStream(bos, bufferSize) {{
				def.setLevel(Deflater.BEST_SPEED);
			}}
		) {
			FileCopyUtils.copy(original, gos);
			return bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't encode to gzip", e);
		}
	}

	private byte[] encodeGzip(T t) {
		try (
			ByteArrayOutputStream bos = new ByteArrayOutputStream(bufferSize);
			GZIPOutputStream gos = new GZIPOutputStream(bos, bufferSize) {{
				def.setLevel(Deflater.BEST_SPEED);
			}}
		) {
			objectMapper.writeValue(gos, t);
			return bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't encode to gzip", e);
		}
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null) {
			return null;
		}
		try {
			if (isGzipCompressed(bytes)) {
				byte[] rawBytes = decodeGzip(bytes);
				return objectMapper.readValue(rawBytes, 0, rawBytes.length, typeRef);
			}
			return objectMapper.readValue(bytes, 0, bytes.length, typeRef);
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't deserialize object", e);
		}
	}

	private byte[] decodeGzip(byte[] encoded) {
		try (
			ByteArrayInputStream bis = new ByteArrayInputStream(encoded);
			GZIPInputStream gis = new GZIPInputStream(bis, bufferSize);
			ExposedBufferByteArrayOutputStream out = new ExposedBufferByteArrayOutputStream(bufferSize)
		) {
			FileCopyUtils.copy(gis, out);
			return out.getRawByteArray();
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't decode gzip", e);
		}
	}

	private boolean isGzipCompressed(byte[] bytes) {
		return bytes.length > 2 && bytes[0] == GZIP_MAGIC_BYTES[0] && bytes[1] == GZIP_MAGIC_BYTES[1];
	}

	static class ExposedBufferByteArrayOutputStream extends ByteArrayOutputStream {
		public ExposedBufferByteArrayOutputStream(int size) {
			super(size);
		}

		public byte[] getRawByteArray() {
			return this.buf;
		}
	}
}

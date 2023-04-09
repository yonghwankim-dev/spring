package chap07;

public class ExeMillTimeCalculator implements Calculator{
	private Calculator delegate;

	public ExeMillTimeCalculator(Calculator delegate) {
		this.delegate = delegate;
	}

	@Override
	public long factorial(long num) {
		long start = System.currentTimeMillis();
		long result = delegate.factorial(num);
		long end = System.currentTimeMillis();
		
		System.out.printf("%s.factorial(%d) 실행 시간 = %d\n",delegate.getClass().getSimpleName(), num, (end-start));
		return result;
	}
	
	
	
	
}

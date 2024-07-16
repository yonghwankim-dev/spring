package com.ch19.class04.step03;

import java.util.function.Function;
import java.util.function.Supplier;

public class Expression {
	public interface Expr{}

	static class BinOp implements Expr{
		private String opName;
		private Expr left;
		private Expr right;

		public BinOp(String opName, Expr left, Expr right) {
			this.opName = opName;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return String.format("(%s%s%s)", left, opName, right);
		}
	}

	static class Number implements Expr{
		private int value;

		public Number(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return Integer.toString(value);
		}
	}

	public static Expr simplify(Expr e){
		TriFunction<String, Expr, Expr, Expr> binOpCase = (opName, left, right)->{
			Expr simplifyLeft = simplify(left);
			Expr simplifyRight = simplify(right);

			if ("+".equals(opName)){
				if (simplifyLeft instanceof Number){
					int leftValue = ((Number)simplifyLeft).value;
					if (leftValue == 0){
						return simplifyRight;
					}
				}
				if (simplifyRight instanceof Number){
					int rightValue = ((Number)simplifyRight).value;
					if (rightValue == 0){
						return simplifyLeft;
					}
				}
				return new BinOp(opName, simplifyLeft, simplifyRight);
			}
			if ("*".equals(opName)){
				if (simplifyLeft instanceof Number){
					int value = ((Number)left).value;
					if (value == 1){
						return simplifyRight;
					}
				}
				if (right instanceof Number){
					int value = ((Number)right).value;
					if (value == 1){
						return simplifyLeft;
					}
				}
				return new BinOp(opName, simplifyLeft, simplifyRight);
			}
			if ("/".equals(opName)){
				if (right instanceof Number){
					int value = ((Number)right).value;
					if (value == 1){
						return simplifyLeft;
					}
				}
				return new BinOp(opName, simplifyLeft, simplifyRight);
			}
			return new BinOp(opName, simplifyLeft, simplifyRight);
		};
		Function<Integer, Expr> numCase = Number::new;
		Supplier<Expr> defaultCase = ()->new Number(0);
		return patternMatchExpr(e, binOpCase, numCase, defaultCase);
	}

	static <T> T patternMatchExpr(
		Expr e,
		TriFunction<String, Expr, Expr, T> binOpCase,
		Function<Integer, T> numCase,
		Supplier<T> defaultCase){
		if (e instanceof BinOp){
			BinOp binOp = (BinOp)e;
			String op = binOp.opName;
			Expr left = binOp.left;
			Expr right = binOp.right;
			return binOpCase.apply(op, left, right);
		}else if (e instanceof Number){
			int value = ((Number)e).value;
			return numCase.apply(value);
		}else{
			return defaultCase.get();
		}
	}

	public static void main(String[] args) {
		Expr sevenPlusZero = new BinOp("+", new Number(7), new Number(0));
		Expr fiveMultiplyOne = new BinOp("*", new Number(5), new Number(1));
		// (7 + 0) + (5 * 1)
		Expr twelve = new BinOp("+", sevenPlusZero, fiveMultiplyOne);
		System.out.println("Original Expression: " + twelve);
		Expr result = simplify(twelve);
		System.out.println("Simplified Expression: " + result);

		// (7 + 1) + 0
		Expr sevenPlusOne = new BinOp("+",new Number(7), new Number(1));
		Expr zero = new Number(0);
		Expr eight = new BinOp("+", sevenPlusOne, zero);
		System.out.println("Original Expression: " + eight);
		result = simplify(eight);
		System.out.println("Simplified Expression: " + result);

		// (7 + 1) / 1
		sevenPlusOne = new BinOp("+",new Number(7), new Number(1));
		Expr one = new Number(1);
		eight = new BinOp("/", sevenPlusOne, one);
		System.out.println("Original Expression: " + eight);
		result = simplify(eight);
		System.out.println("Simplified Expression: " + result);
	}
}

package de.admir.taze.util;

import com.spencerwi.either.Either;

import java.util.NoSuchElementException;
import java.util.function.Function;

public abstract class Xor<L, R> extends Either<L, R> {

    public static <L, R> Xor<L, R> left(L left) {
        return new Xor.Left<>(left);
    }

    public static <L, R> Xor<L, R> right(R right) {
        return new Xor.Right<>(right);
    }

    public abstract <T, U> Xor<T, U> map(Function<L, T> transformLeft, Function<R, U> transformRight);

    public <U> Xor<L, U> mapRight(Function<R, U> transformRight) {
        return this.map(left -> left, transformRight);
    }

    public abstract <T, U> Xor<T, U> flatMap(Function<L, Xor<T, U>> transformLeft, Function<R, Xor<T, U>> transformRight);

    public abstract <U> Xor<L, U> flatMapRight(Function<R, Xor<L, U>> transformRight);

    private static class Left<L, R> extends Xor<L, R> {
        private Left(L left) {
            this.leftValue = left;
            this.rightValue = null;
        }

        @Override
        public L getLeft() {
            return this.leftValue;
        }

        @Override
        public R getRight() {
            throw new NoSuchElementException("Tried to getRight from a Left");
        }

        @Override
        public boolean isLeft() {
            return true;
        }

        @Override
        public boolean isRight() {
            return false;
        }

        @Override
        public <T> T fold(Function<L, T> transformLeft, Function<R, T> transformRight) {
            return transformLeft.apply(this.leftValue);
        }

        @Override
        public <T, U> Xor<T, U> map(Function<L, T> transformLeft, Function<R, U> transformRight) {
            return Xor.left(transformLeft.apply(this.leftValue));
        }

        @Override
        public <T, U> Xor<T, U> flatMap(Function<L, Xor<T, U>> transformLeft, Function<R, Xor<T, U>> transformRight) {
            return transformLeft.apply(this.leftValue);
        }

        @Override
        public <U> Xor<L, U> flatMapRight(Function<R, Xor<L, U>> transformRight) {
            return Xor.left(this.leftValue);
        }
    }

    private static class Right<L, R> extends Xor<L, R> {
        private Right(R right) {
            this.leftValue = null;
            this.rightValue = right;
        }

        @Override
        public L getLeft() {
            throw new NoSuchElementException("Tried to getLeft from a Right");
        }

        @Override
        public R getRight() {
            return rightValue;
        }

        @Override
        public boolean isLeft() {
            return false;
        }

        @Override
        public boolean isRight() {
            return true;
        }

        @Override
        public <T> T fold(Function<L, T> transformLeft, Function<R, T> transformRight) {
            return transformRight.apply(this.rightValue);
        }

        @Override
        public <T, U> Xor<T, U> map(Function<L, T> transformLeft, Function<R, U> transformRight) {
            return Xor.right(transformRight.apply(this.rightValue));
        }

        @Override
        public <T, U> Xor<T, U> flatMap(Function<L, Xor<T, U>> transformLeft, Function<R, Xor<T, U>> transformRight) {
            return transformRight.apply(this.rightValue);
        }

        @Override
        public <U> Xor<L, U> flatMapRight(Function<R, Xor<L, U>> transformRight) {
            return transformRight.apply(this.rightValue);
        }
    }
}

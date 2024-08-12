package com.bank.boubyan.model.dto;

public class LibraryMSResponse <T>{
    private final T data;
    private final long offset;
    private final long limit;
    private final long totalCount;
    private final String moreDetails;
    public LibraryMSResponse(Builder<T> builder) {
        this.data = builder.data;
        this.offset = builder.offset;
        this.limit = builder.limit;
        this.totalCount = builder.totalCount;
        this.moreDetails = builder.moreDetails;
    }

    public static class Builder<T>{
        private T data;
        private long offset;
        private long limit;
        private long totalCount;
        private String moreDetails;

        public Builder<T> offset(long offset) {
            this.offset = offset;
            return this;
        }
        public Builder<T> limit(long limit) {
            this.limit = limit;
            return this;
        }
        public Builder<T> totalCount(long totalCount) {
            this.totalCount = totalCount;
            return this;
        }
        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> data(String moreDetails) {
            this.moreDetails = moreDetails;
            return this;
        }
        public LibraryMSResponse<T> build(){
            return new LibraryMSResponse<T>(this);
        }
    }

    public T getData() {
        return data;
    }

    public long getOffset() {
        return offset;
    }

    public long getLimit() {
        return limit;
    }
    public long getTotalCount() {
        return totalCount;
    }
    public String getMoreDetails() {
        return moreDetails;
    }
    @Override
    public String toString() {
        return "LibraryMSResponse{" +
                "data=" + data +
                ", offset=" + offset +
                ", limit=" + limit +
                ", totalCount=" + totalCount +
                ", moreDetails='" + moreDetails + '\'' +
                '}';
    }
}

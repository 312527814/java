package com.my._2_lock;

public class MyReadWriteLock {
    private MyRWAQS aqs = new MyRWAQS();

    public ReadLock readLock() {
        return new ReadLock(aqs);
    }

    public WriteLock writeLock() {
        return new WriteLock(aqs);
    }

    public class ReadLock {
        private MyRWAQS sync;

        public ReadLock(MyRWAQS aqs) {
            sync = aqs;
        }


        public void lock() {
            sync.shareAcquire(1);
        }

        public void unlock() {
            sync.shareRelease(1);
        }

    }

    public class WriteLock {
        private MyRWAQS sync;

        public WriteLock(MyRWAQS aqs) {
            sync = aqs;
        }


        public void lock() {
            sync.acquire(1);
        }

        public void unlock() {
            sync.release(1);
        }
    }
}

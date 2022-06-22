package com.my._5_threadpool;


public class TT6 {
    public TestTheard testTheard;
    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 1; i++) {
            TT6 tt6 = new TT6();
            tt6.testTheard = new TestTheard(i + "");
            tt6.testTheard.start();
            tt6.close();
        }

        System.in.read();

    }


    protected void close()  {
        String name = this.testTheard.getName();
        this.testTheard.interrupt();
        System.out.println(name+" TT6....finalize");
    }

        static class TestTheard extends Thread{
        @Override
        protected void finalize() throws Throwable {
            String name = super.getName();
            System.out.println(name+" ..finalize....");
        }

        public TestTheard(String name){
            super(name);
        }

        @Override
        public void run() {
            boolean b= true;

            while (b) {
                try {
                    long a=99999L;
                    while (a>0){
                        a--;
                        System.out.println("ddddddd");
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    b = false;
                }

                System.out.println(super.getName()+"......");
            }

        //    System.out.println( "top  ......");
        }


    }
}

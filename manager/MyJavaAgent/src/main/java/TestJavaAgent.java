import java.lang.instrument.Instrumentation;

public class TestJavaAgent {
    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("=========premain方法执行========");
        inst.addTransformer(new ClassFileTransformerImp());

        //System.out.println(agentOps);
        // 添加Transformer
//        Class[] allLoadedClasses = inst.getAllLoadedClasses();
//        for (Class allLoadedClass : allLoadedClasses) {
//
//            System.out.print("myAgent.premain=>" + allLoadedClass.getName() + "\r\n");
//        }
    }
    public static void premain(String agentArgs) {
        System.out.print("myAgent.premain=>" + agentArgs + "\r\n");

        new Thread(() -> {
            // 虚拟机级内存情况查询
            long vmFree = 0;
            long vmUse = 0;
            long vmTotal = 0;
            long vmMax = 0;
            int byteToMb = 1024 * 1024;
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Runtime rt = Runtime.getRuntime();
                vmTotal = rt.totalMemory() / byteToMb;
                vmFree = rt.freeMemory() / byteToMb;
                vmMax = rt.maxMemory() / byteToMb;
                vmUse = vmTotal - vmFree;
                System.out.println("\r\nJVM内存已用的空间为：" + vmUse + " MB");
                System.out.println("\r\nJVM内存的空闲空间为：" + vmFree + " MB");
                System.out.println("\r\nJVM总内存空间为：" + vmTotal + " MB");
                System.out.println("\r\nJVM总内存空间为：" + vmMax + " MB");

                System.out.println("\r\n...........................................");
            }
        }).start();


    }
}

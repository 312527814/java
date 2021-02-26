import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ClassFileTransformerImp implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//        System.out.print("\r\n 加载类：" + className);
//
        if(className.contains("my")){
            System.out.print("\r\n 加载类：" + className);
            System.out.print("\r\n 加载类：" + className);
            System.out.print("\r\n 加载类：.........................................................................." );
            System.out.print("\r\n 加载类：.........................................................................." );
            System.out.print("\r\n 加载类：.........................................................................." );
            System.out.print("\r\n 加载类：.........................................................................." );


        }
        return new byte[0];
    }
}

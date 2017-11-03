package code.test4;

import java.io.*;


public class Prototype  implements  Cloneable {



    private String name;
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //浅复制
    public Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }

    //深复制
    Prototype deepClone() throws IOException, ClassNotFoundException {
         /* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Prototype)ois.readObject();
    }

    @Override
    public String toString() {
        return "Prototype{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}

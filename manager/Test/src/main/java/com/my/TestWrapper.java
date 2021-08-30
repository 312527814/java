package com.my;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-08-30 13:47
 */
public class TestWrapper {
    public static void main(String[] args) {
        PropertyValue propertyValueId=new PropertyValue("id",1);
        TestWrapper testWrapper = new TestWrapper();
        PropertyValue propertyValueWrapper = new PropertyValue("wrapper", testWrapper);

        User user = new User();
        BeanWrapperImpl wrapper=new BeanWrapperImpl(user);
        wrapper.setPropertyValue(propertyValueId);
        wrapper.setPropertyValue(propertyValueWrapper);
        Object rootInstance = wrapper.getRootInstance();
    }

    public static  class  User {
        private  int id;
        private  String name;
        private  TestWrapper wrapper;

        public TestWrapper getWrapper() {
            return wrapper;
        }

        public void setWrapper(TestWrapper wrapper) {
            this.wrapper = wrapper;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

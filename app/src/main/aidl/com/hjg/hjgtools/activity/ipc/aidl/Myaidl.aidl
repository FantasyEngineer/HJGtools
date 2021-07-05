// Myaidl.aidl
package com.hjg.hjgtools.activity.ipc.aidl;//注意这里的包名

// Declare any non-default types here with import statements
// though sample packet,but must be import opreation
import com.hjg.hjgtools.activity.ipc.aidl.Person;//注意这里的引用

interface Myaidl {

    void addPerson(in Person person);

    List<Person> getPersonList();
}
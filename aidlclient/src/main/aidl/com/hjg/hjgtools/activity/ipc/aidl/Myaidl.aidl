// Myaidl.aidl
package com.hjg.hjgtools.activity.ipc.aidl;//attention this package

// Declare any non-default types here with import statements
// though sample packet,but must be import opreation
import com.hjg.hjgtools.activity.ipc.aidl.Person;//attention import

interface Myaidl {

    void addPerson(in Person person);

    List<Person> getPersonList();
}
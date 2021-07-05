package com.hjg.hjgtools.activity.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.hjg.base.util.log.androidlog.L;

import java.util.ArrayList;
import java.util.List;

public class AidlService extends Service {
    private ArrayList<Person> persons;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        L.d("success onBind");
        return iBinder;
    }


    private IBinder iBinder = new Myaidl.Stub() {

        @Override
        public void addPerson(Person person) throws RemoteException {
            persons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return persons;
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        L.d("onCreate success");
    }

}

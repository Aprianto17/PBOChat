package com.example.aprianto.pbochat;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Aprianto on 12/29/2017.
 */

public class Chat implements Parcelable {

    private String Pesan;
    private User sender;
    private Long tanggal;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference chatref = database.getReference("message");

    void send(){
        chatref.push().setValue(this);
    }

    public void Chat() {
    }

//    Getter and Setter
    public String getPesan() {
        return Pesan;
    }

    public void setPesan(String pesan) {
        Pesan = pesan;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Long getTanggal() {
        return tanggal;
    }

    public void setTanggal(Long tanggal) {
        this.tanggal = tanggal;
    }

    //parcilable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Pesan);
        dest.writeParcelable(this.sender, flags);
        dest.writeValue(this.tanggal);
    }

    public Chat() {
    }

    protected Chat(Parcel in) {
        this.Pesan = in.readString();
        this.sender = in.readParcelable(User.class.getClassLoader());
        this.tanggal = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Chat> CREATOR = new Parcelable.Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel source) {
            return new Chat(source);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };
}

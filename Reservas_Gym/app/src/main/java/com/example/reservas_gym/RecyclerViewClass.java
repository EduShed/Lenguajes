package com.example.reservas_gym;

public class RecyclerViewClass {
    private int imageResource;
    private String studentId, reservationTime;

    public RecyclerViewClass(int imageResource, String studentName, String reservationTime)
    {
        this.imageResource = imageResource;
        this.studentId = studentName;
        this.reservationTime = reservationTime;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getReservationTime() {
        return reservationTime;
    }
}

package com.example.reservas_gym;

public class ViewPagerModel {
    private int studentImage;
    private String studentId;
    private String reservationTime;

    public ViewPagerModel(int studentImage, String studentId, String reservationTime) {
        this.studentImage = studentImage;
        this.studentId = studentId;
        this.reservationTime = reservationTime;
    }

    public int getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(int studentImage) {
        this.studentImage = studentImage;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }
}

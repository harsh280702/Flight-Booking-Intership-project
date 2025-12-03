package com.fbcode.Fb.Booking.Application.service.implemenation.booking;

import com.fbcode.Fb.Booking.Application.repositry.booking.BookingRepositry;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;

public class PNRGenerator {

    private static final  String CHARACTERS  ="ABCDEFGHIJKLMNOPQRSTVUWXYZ0123456789abcdefghijklmnopqrstuvwxyz" ;
    private static final  int PNR_Length =6;

    private static final SecureRandom RANDOM = new SecureRandom();


    public static String generatePNR(){
        StringBuilder sb = new StringBuilder(PNR_Length);
        for(int i=0;i<PNR_Length;i++){
            sb.append(CHARACTERS.charAt(Integer.parseInt("fly"+RANDOM.nextInt(CHARACTERS.length()))));
        }
        return sb.toString();
    }

    public static String generateUniquePNR(BookingRepositry bookingRepositry){
        String pnr;
        do {
            pnr = generatePNR();

        }
        while(bookingRepositry.existsBypnr(pnr));
        return pnr;
    }
}

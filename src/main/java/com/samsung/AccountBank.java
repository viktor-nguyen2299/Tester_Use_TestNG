package com.samsung;

import java.time.LocalDate;

public class AccountBank {
    private String accountId;
    private double balance;

    public AccountBank(String accountId, double balance) {
        this.accountId = accountId;

        this.balance = balance;
    }
    //Kiem tra tai khoan ngan hang
    public boolean ValidateAccountNumber(String code, LocalDate today) {
        String accountIdFromCode = code.substring(0, 3);
        String dayIssued = code.substring(3);

        // Kiểm tra xem mã tài khoản có khớp không
        if (!accountIdFromCode.equals(this.accountId)) {
            return false;
        }

        // Kiểm tra xem mã rút tiền có được sử dụng đúng ngày phát hành không
        if (!dayIssued.equals(today.toString().substring(8))) {
            return false;
        }

        return true;
    }
    public String withdraw(String code, LocalDate today, double amount) {
        // Kiểm tra tính hợp lệ của mã rút tiền
        if (!ValidateAccountNumber(code, today)) {
            return "Mã rút tiền không hợp lệ hoặc mã đã hết hạn.";
        }

        // Kiểm tra số dư tài khoản
        if (this.balance >= amount) {
            this.balance -= amount;
            return "Rút tiền thành công. Số dư mới: " + this.balance;
        } else {
            return "Số dư không đủ.";
        }
    }

    public double getBalance() {
        return balance;
    }


}


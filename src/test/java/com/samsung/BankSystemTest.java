package com.samsung;

import com.samsung.BankSystemTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.LocalDate;

public class BankSystemTest {

    @DataProvider(name = "withdrawalData")
    public Object[][] provideWithdrawalData() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        return new Object[][]{
                // Trường hợp rút tiền hợp lệ
                {"AAA", 5000, "AAA" + today.toString().substring(8), today, 1000, "Rút tiền thành công. Số dư mới: 4000.0"},

                // Trường hợp mã không hợp lệ
                {"AAA", 5000, "AAB" + today.toString().substring(8), today, 1000, "Mã rút tiền không hợp lệ hoặc mã đã hết hạn."},

                // Trường hợp số dư không đủ
                {"AAA", 500, "AAA" + today.toString().substring(8), today, 1000, "Số dư không đủ."},

                // Trường hợp mã hết hạn
                {"AAA", 5000, "AAA" + yesterday.toString().substring(8), today, 1000, "Mã rút tiền không hợp lệ hoặc mã đã hết hạn."}
        };
    }

    @Test(dataProvider = "withdrawalData")
    public void testWithdrawal(String accountId, double initialBalance, String code, LocalDate today, double amount, String expectedMessage) {
        AccountBank bank = new AccountBank(accountId, initialBalance);
        String result = bank.withdraw(code, today, amount);
        Assert.assertEquals(result, expectedMessage);
    }
}

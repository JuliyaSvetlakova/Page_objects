package ru.netology.remittance.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.remittance.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement transferHeading = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-test-id='error-notification']");

    public TransferPage(){
        transferHeading.shouldBe(visible);
    }
    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }
    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }
    public void makeTransferWithoutCardNumber(String amountToTransfer) {
        amountInput.setValue(amountToTransfer);
        transferButton.click();
    }

    public void makeTransferWithoutAmount(DataHelper.CardInfo cardInfo) {
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expectedText) {
       errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(20)).shouldBe(visible);
    }
}
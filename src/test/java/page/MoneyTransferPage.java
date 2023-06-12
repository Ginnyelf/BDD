package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MoneyTransferPage {
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement cardFrom = $("[data-test-id=from] input");
    private SelenideElement cardTo = $("[data-test-id=to] input");
    private SelenideElement depositButton = $("[data-test-id=action-transfer]");
    private SelenideElement messageError = $("[data-test-id=error-notification]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public MoneyTransferPage() {
        $x("//*[contains(text(), 'Пополнение карты')]").shouldBe(Condition.visible);
    }

    public DashboardPage deposit(int depositAmount, String sourceCard) {
        setAmount(depositAmount);
        setSourceCard(sourceCard);
        depositButton.click();
        return new DashboardPage();
    }

    public void setSourceCard(String sourceCard) {
        cardFrom.sendKeys(Keys.CONTROL + "A");
        cardFrom.sendKeys(Keys.DELETE);
        cardFrom.setValue(sourceCard);
    }

    public void setAmount(int depositAmount) {
        getAmount().sendKeys(Keys.CONTROL + "A");
        getAmount().sendKeys(Keys.DELETE);
        getAmount().setValue(Integer.toString(depositAmount));
    }

    public void checkErrorVisible() {
        messageError.shouldBe(Condition.visible);
    }

    public void clickCancel() {
        cancelButton.click();
    }


    public SelenideElement getAmount() {
        return amount;
    }
    public void specialSymbolAndLettersInAmountField(){
        getAmount().sendKeys(Keys.CONTROL + "A");
        getAmount().sendKeys(Keys.DELETE);
        getAmount().setValue("-+/").shouldBe(Condition.empty);
        getAmount().setValue("asdf").shouldBe(Condition.empty);

    }

}

package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

public class AndroidTests extends TestBase {
    @Test
    void successfulSearchTest() {
        step("Click to search field", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });

        step("Search for Sherlock", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Sherlock");
        });

        step("Check that results are not empty", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0));
        });
    }

    @Test
    void errorWhenOpeningArticleTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });
        step("Search for Bring me the horizon", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Bring me the horizon");
        });
        step("Click on the first article", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_container")).first().click());
        step("Check error message", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldHave(Condition.text("An error occurred")));

    }
}

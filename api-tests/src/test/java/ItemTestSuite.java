import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        CreateGoodTests.class,
        GetGoodsTests.class,
        GetGoodByIdTest.class,
        ChangeGoodTests.class,
        DeleteGoodTest.class
})
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class ItemTestSuite {
}
//package ua.vlad.huntinggrounds;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import ua.vlad.huntinggrounds.util.Strings;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class HuntingGroundsApplicationTests {
//
//    @Test
//    public void stringUtils() {
//        String result = Strings.replace("Hunting ground: {0}. Area: {1} ha.", new Object[] {"\"Trypillian Hunter\"", 49400});
//        System.out.println(result);
//        Assert.assertEquals(result, "Hunting ground: \"Trypillian Hunter\". Area: 49400 ha.");
//
//        try {
//            throw new ApplicationException(Error.INVALID_USERNAME_OR_PASSWORD);
//        } catch (ApplicationException ignored) {}
//    }
//}

package service.log;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LogServiceTest {


    LogService cut = new LogService("School/src/main/java/file/log.txt");

    static Arguments[] writeToFileTestArgs(){
        return new Arguments[]{
                Arguments.arguments(),
        };
    }

    @MethodSource("writeToFileTestArgs")
    @ParameterizedTest
    void writeToFileTest(){

    }

}

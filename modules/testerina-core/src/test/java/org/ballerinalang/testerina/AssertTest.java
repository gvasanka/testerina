/*
*   Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.ballerinalang.testerina;

import org.ballerinalang.model.values.BBoolean;
import org.ballerinalang.model.values.BFloat;
import org.ballerinalang.model.values.BInteger;
import org.ballerinalang.model.values.BString;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.util.codegen.ProgramFile;
import org.ballerinalang.util.exceptions.BLangRuntimeException;
import org.ballerinalang.util.program.BLangFunctions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test cases for ballerina.test package.
 */
public class AssertTest {

    ProgramFile programFile;

    @BeforeClass
    public void setup() {
        programFile = BTestUtils.getProgramFile("assert-test/assert-test.bal");
    }

    @Test
    public void testAssertIntEquals() {
        BValue[] args = {new BInteger(3), new BInteger(5)};
        BLangFunctions.invokeNew(programFile, "testAssertIntEquals", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class, expectedExceptionsMessageRegExp = ".*intAdd function " +
            "failed expected \\[8\\] but found \\[6\\].*")
    public void testAssertIntEqualsFail() {
        BValue[] args = {new BInteger(3), new BInteger(3)};
        BLangFunctions.invokeNew(programFile, "testAssertIntEquals", args);
    }

    @Test
    public void testAssertFloatEquals() {
        BValue[] args = {new BFloat(10.000), new BFloat(20.050)};
        BLangFunctions.invokeNew(programFile, "testAssertFloatEquals", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class, expectedExceptionsMessageRegExp = ".*floatAdd function " +
            "failed expected \\[30.05\\] but found \\[21.05\\].*")
    public void testAssertFloatEqualsFail() {
        BValue[] args = {new BFloat(1), new BFloat(20.050)};
        BLangFunctions.invokeNew(programFile, "testAssertFloatEquals", args);
    }

    @Test
    public void testAssertStringEquals() {
        BValue[] args = {new BString("John"), new BString("Doe")};
        BLangFunctions.invokeNew(programFile, "testAssertStringEquals", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class, expectedExceptionsMessageRegExp = ".*string concatenation" +
            " failed expected \\[JohnDoe\\] but found \\[DoeJohn\\].*")
    public void testAssertStringEqualsFail() {
        BValue[] args = {new BString("Doe"), new BString("John")};
        BLangFunctions.invokeNew(programFile, "testAssertStringEquals", args);
    }

    @Test
    public void testAssertTrue() {
        BValue[] args = {new BBoolean(true)};
        BLangFunctions.invokeNew(programFile, "testAssertTrue", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class,
            expectedExceptionsMessageRegExp = ".*assertTrue failed.*")
    public void testAssertTrueFail() {
        BValue[] args = {new BBoolean(false)};
        BLangFunctions.invokeNew(programFile, "testAssertTrue", args);
    }

    @Test
    public void testAssertFalse() {
        BValue[] args = {new BBoolean(false)};
        BLangFunctions.invokeNew(programFile, "testAssertFalse", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class,
            expectedExceptionsMessageRegExp = ".*assertFalse failed.*")
    public void testAssertFalseFail() {
        BValue[] args = {new BBoolean(true)};
        BLangFunctions.invokeNew(programFile, "testAssertFalse", args);
    }

    @Test
    public void testAssertBooleanEquals() {
        BValue[] args = {new BBoolean(false), new BBoolean(false)};
        BLangFunctions.invokeNew(programFile, "testAssertBooleanEquals", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class, expectedExceptionsMessageRegExp = ".*assertBooleanEquals" +
            " failed expected \\[false\\] but found \\[true\\].*")
    public void testAssertBooleanEqualsFail() {
        BValue[] args = {new BBoolean(true), new BBoolean(false)};
        BLangFunctions.invokeNew(programFile, "testAssertBooleanEquals", args);
    }

    @Test
    public void testNoAssertFail() {
        BValue[] args = {new BBoolean(true)};
        BLangFunctions.invokeNew(programFile, "testAssertFail", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class,
            expectedExceptionsMessageRegExp = ".*assertFailed.*")
    public void testAssertFail() {
        BValue[] args = {new BBoolean(false)};
        BLangFunctions.invokeNew(programFile, "testAssertFail", args);
    }

    @Test
    public void testAssertStringArrayEquals0() {
        BValue[] args = {new BInteger(0)};
        BLangFunctions.invokeNew(programFile, "testAssertStringArrayEquals", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class,
            expectedExceptionsMessageRegExp = ".*failed \\(Array lengths are not the same\\).*")
    public void testAssertStringArrayEquals1() {
        BValue[] args = {new BInteger(1)};
        BLangFunctions.invokeNew(programFile, "testAssertStringArrayEquals", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class,
            expectedExceptionsMessageRegExp = ".*failed. expected \\[b\\] but found \\[B\\] \\(at index 1\\).*")
    public void testAssertStringArrayEquals2() {
        BValue[] args = {new BInteger(2)};
        BLangFunctions.invokeNew(programFile, "testAssertStringArrayEquals", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class, expectedExceptionsMessageRegExp = ".*arrays are not equal" +
            ". expected \\[b\\] but found \\[B\\] \\(at index 1\\).*")
    public void testAssertStringArrayEquals3() {
        BValue[] args = {new BInteger(3)};
        BLangFunctions.invokeNew(programFile, "testAssertStringArrayEquals", args);
    }

    @Test
    public void testAssertIntArrayEquals0() {
        BValue[] args = {new BInteger(0)};
        BLangFunctions.invokeNew(programFile, "testAssertIntArrayEquals", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class,
            expectedExceptionsMessageRegExp = ".*failed \\(Array lengths are not the same\\).*")
    public void testAssertIntArrayEquals1() {
        BValue[] args = {new BInteger(1)};
        BLangFunctions.invokeNew(programFile, "testAssertIntArrayEquals", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class,
            expectedExceptionsMessageRegExp = ".*failed. expected \\[5\\] but found \\[2\\] \\(at index 1\\).*")
    public void testAssertIntArrayEquals2() {
        BValue[] args = {new BInteger(2)};
        BLangFunctions.invokeNew(programFile, "testAssertIntArrayEquals", args);
    }

    @Test
    public void testAssertFloatArrayEquals0() {
        BValue[] args = {new BInteger(0)};
        BLangFunctions.invokeNew(programFile, "testAssertFloatArrayEquals", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class,
            expectedExceptionsMessageRegExp = ".*failed \\(Array lengths are not the same\\).*")
    public void testAssertFloatArrayEquals1() {
        BValue[] args = {new BInteger(1)};
        BLangFunctions.invokeNew(programFile, "testAssertFloatArrayEquals", args);
    }

    @Test(expectedExceptions = BLangRuntimeException.class,
            expectedExceptionsMessageRegExp = ".*failed. expected \\[2.22\\] but found \\[2.2\\] \\(at index 1\\).*")
    public void testAssertFloatArrayEquals2() {
        BValue[] args = {new BInteger(2)};
        BLangFunctions.invokeNew(programFile, "testAssertFloatArrayEquals", args);
    }
}

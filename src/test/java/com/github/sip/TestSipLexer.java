/**
 *   Copyright 2016 Pratapi Hemant Patel
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *   
 */
package com.github.sip;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.github.sip.SipUtils;

public class TestSipLexer {

    @Test
    public void testSipUriWithHostName() {

        assertTrue(SipUtils.isValidSipUri("sip:abc.com"));

    }

    @Test
    public void testSipUriWithUserInfoHostName() {

        assertTrue(SipUtils.isValidSipUri("sip:hemant@abc.github.com"));
    }

    @Test
    public void testSipUriWithUserInfoPasswordHostName() {

        assertTrue(SipUtils.isValidSipUri("sip:hemant:patel@abc-github.hemant.com"));
    }

    @Test
    public void testSipUriWithUriParameters() {

        assertTrue(SipUtils.isValidSipUri("sip:hemant:patel@abc.com;key=value"));
    }

    @Test
    public void testSipUriWithTwoUriParameters() {

        assertTrue(SipUtils.isValidSipUri("sip:hemant:patel@abc.com;key1=value1;key2"));
    }

    @Test
    public void testSipUriWithUriParametersAndHeaders() {

        assertTrue(SipUtils.isValidSipUri("sip:hemant:patel@abc.com;key1=value1;key2?headerKey=headerValue"));
    }

    @Test
    public void testSipUriWithIP() {

        assertTrue(SipUtils
                .isValidSipUri("sip:hemant:patel@192.168.1.1;key1?headerKey1=headerValue1&headerKey2=headerValue2"));
    }

    @Test
    public void testSipUriWithIPPort() {

        assertTrue(SipUtils
                .isValidSipUri("sip:hemant:patel@192.168.1.1:80;key1?headerKey1=headerValue1&headerKey2=headerValue2"));
    }

    @Test
    public void testSipsUriWithIPPort() {

        assertTrue(SipUtils
                .isValidSipUri("sips:hemant:patel@192.168.1.1:80;key1?headerKey1=headerValue1&headerKey2=headerValue2"));
    }

    @Test
    public void testSipUriWithHostNotAvailable() {

        assertFalse(SipUtils.isValidSipUri("sip:hemant:patel@"));
    }
    
    @Test
    public void testSipUriInvalidScheme() {

        assertFalse(SipUtils.isValidSipUri("sipsss:hemant:patel@abc.com"));
    }
}

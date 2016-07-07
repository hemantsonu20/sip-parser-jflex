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

import java.io.IOException;
import java.io.StringReader;

import java_cup.sym;
import jflex.SipLexer;

public class SipUtils {

    /**
     * Method to validate a sip/sips uri based on the rfc
     * https://www.ietf.org/rfc/rfc3261.txt
     * 
     * @param uri
     *            the uri to be validated
     * @return whether sip uri is valid or not
     */
    public static boolean isValidSipUri(String uri) {

        StringReader reader = new StringReader(uri);
        SipLexer lexer = new SipLexer(reader);

        int count = 0;
        try {
            while (lexer.next_token().sym != sym.EOF) {
                count++;
            }
        } catch (IOException e) {
            return false;
        }

        // only one token should be found
        return count == 1;
    }
}

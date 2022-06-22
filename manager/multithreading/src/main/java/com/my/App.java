package com.my;

import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        String ff="groupId|ddddd";
        String groupId="group2Id";
        String pattern = "^" + groupId + "|.*";
        boolean result = Pattern.matches(pattern, ff);
    }
}

package com.example.comma_counter_;

public class comma_count {

    public static String count_commas(String text)
    {
        if(text!="")
        {
            int counter = 0;
            for(int i =0; i<text.length(); i++)
            {
                char c = text.charAt(i);
                if(c == ',')
                {
                    counter++;
                }

            }
            return String.valueOf(counter);
        }
        else
        {
            return "Please enter a valid string";
        }

    }
}

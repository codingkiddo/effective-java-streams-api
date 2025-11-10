You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices in a string (not necessarily different) and swap the characters at these indices.

Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of the strings. Otherwise, return false.

 

Example 1:

Input: s1 = "bank", s2 = "kanb"
Output: true
Explanation: For example, swap the first character with the last character of s2 to make "bank".
Example 2:

Input: s1 = "attack", s2 = "defend"
Output: false
Explanation: It is impossible to make them equal with one string swap.
Example 3:

Input: s1 = "kelb", s2 = "kelb"
Output: true
Explanation: The two strings are already equal, so no string swap operation is required.
 

Constraints:

1 <= s1.length, s2.length <= 100
s1.length == s2.length
s1 and s2 consist of only lowercase English letters.





Intuition
When comparing two strings, we need to determine if we can make them equal by swapping at most one pair of characters. If the strings are already identical, we can return true immediately. Otherwise, we need to check if exactly two characters can be swapped to make the strings equal.

Approach
Initialize Variables:
Use variables i and j to store the indices of characters that differ between the two strings. Initialize them to -1.
Use a counter cnt to count the number of differing positions.
Iterate Through the Strings:
Traverse each character of the strings using a loop.
If characters at the current position differ, increment the counter cnt.
Store the index of the differing characters in i and j when cnt is 1 and 2 respectively.
Check Conditions:
If cnt is 0, the strings are already equal, so return true.
If cnt is 2, check if swapping the characters at positions i and j in one string makes it equal to the other string.
If both conditions are met, return true; otherwise, return false.
Complexity
Time complexity: O(n)
Space complexity: O(1)
Code
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int i=-1, j=-1;
        int cnt=0;

        for(int k=0; k<s1.length(); k++){
            if(s1.charAt(k)!=s2.charAt(k)){
                cnt++;
                if(i==-1) i=k;
                else if(j==-1) j=k;
            }
        }

        if(cnt==0) return true;
        else if(cnt==2 && s1.charAt(i)==s2.charAt(j) && s1.charAt(j)==s2.charAt(i)){
            return true;
        }


        return false;
    }
}

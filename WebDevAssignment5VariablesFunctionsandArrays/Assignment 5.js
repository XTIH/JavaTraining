// Question 1
document.write("Question 1 - Fibonacci Sequence","<br>");
var num1 = 0;
var num2 = 1;
var num3;

function fibonacciNum(num, num1, num2)
{
    for(var i=1;i<=num-2;i++)
    {
        if(i==1)
        {
            document.write(num1,", ",num2,", ");
            num3 = num1+num2;
            num1 = num2;
            num2 = num3;
            document.write(num3,", ");
        }
        else if(i!=num-2)
        {
            num3 = num1+num2;
            num1 = num2;
            num2 = num3;
            document.write(num3,", ");
        }
        else
        {
            num3 = num1+num2;
            num1 = num2;
            num2 = num3;
            document.write(num3);
        }
    }
}
fibonacciNum(10,num1,num2);
document.write("<br>");
document.write("<br>");

// Question 2
document.write("Question 2 - Table of 8","<br>");

function tableOfEight(number)
{
    if(Math.sign(number)==1)
    {
        for(var i=1;i<=number;i++)
        {
            if(i!=number)
            {
                document.write(i*8,", ");
            }
            else
            {
                document.write(i*8);
            }
        }
    }
}
tableOfEight(12);

document.write("<br>");
document.write("<br>");

// Question 3
document.write("Question 3 - Sorting items in an array","<br>");
var arr1 = [3,8,7,6,5,-4,-3,2,1];
arr1.sort(compare)
document.write(arr1,"<br>");
document.write("<br>");
function compare(a,b)
{
    return a-b;
}

// Question 4
document.write("Question 4 - Printing elements (nested array)","<br>");
var a = [[1,2,1,24],[8,11,9,4], [7,0,7,27], [7,4,28,14],[3,10,26,7]];
for(var i=0;i<a.length;i++)
{
    document.write("row "+i,"<br>");
    for(var j=0;j<a[i].length;j++)
    {
        a[i].sort(compare);
        document.write(a[i][j],"<br>");
    }
}
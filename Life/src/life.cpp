#include <iostream>
#include "console.h"

using namespace std;

// Milestone: print num
void IsPerfect(int num)
{
	// cout << num << endl;
	int newNum = num / 2;
	int sum = 0;
	for (int i = 1; i <= newNum; i++)
	{
		if (num % i == 0)
		{
			sum += i;
		}
	}
	// cout << sum << endl;

	if (num == sum)
	{
		cout << "Perfect number" << endl;
		cout << num << endl;
	}
}

int main()
{
	// cout << Hello World! << endl;
	for (int i = 1; i <= 1000; i++)
	{
		IsPerfect(i);
	}
	return 0;
}

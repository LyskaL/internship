package llyska.services;

class CalculatorServiceImpl implements CalculatorService {
	
	@Override
	public double count(String fromNumber, String toNumber, char symbol) {
		double firstNumber = 0;
		double secondNumber = 0;
		double result = 0;
		if (OperandValidatorImpl.isValidString(fromNumber) 
				&& OperandValidatorImpl.isValidString(toNumber)) {
			firstNumber = Double.parseDouble(fromNumber);
			secondNumber = Double.parseDouble(toNumber);
		} else {
			// TODO error exception
		}

		switch (symbol) {
		case '+':
			result = firstNumber + secondNumber;
			break;
		case '-':
			result = firstNumber - secondNumber;
			break;
		case '*':
			result = firstNumber * secondNumber;
			break;
		case '/':
			if (firstNumber == 0.0) {
				// TODO error exception
			}
			result = firstNumber / secondNumber;
			break;
		}

		return result;
	}

}

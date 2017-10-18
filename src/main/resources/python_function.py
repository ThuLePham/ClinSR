#script (python)

#from gringo import Fun
#import gringo
import sys




def getIntegerValue(value):
	v = value.split("^")[0]
	print int(float(v))
	return int(float(v))
	
def getStringValue(value):
	v = value.split("^")[0]
	print v
	return v

#def main():
#    getIntegerValue("1.3^^http://www.w3.org/2001/XMLSchema#float")

#if __name__ == "__main__":
#    main()
#end.
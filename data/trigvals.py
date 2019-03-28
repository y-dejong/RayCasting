import math

max = .1

file = open("/home/yasper/Documents/APCS/RayCasting/data/trigvals.txt", "w")
xSteps = []
ySteps = []
dSteps = []
for i in range(0, 90) :
    tani = math.tan(i*(math.pi/180))
    divisor = 1/max
    if tani > max:
        divisor = tani/max
    print("divisor: " + str(divisor))
    xStep = 1/divisor
    yStep = tani/divisor
    dStep = math.sqrt(xStep**2 + yStep ** 2)
    xSteps.append(xStep)
    ySteps.append(yStep)
    dSteps.append(dStep)
    file.write(str(i) + " " + str(xStep) + " " +
               str(yStep) + " " + str(dStep) + "\n")

# Special case 90
file.write("90 0 .1 .1\n")

# reflection over y axis
for i in range(1, 90):
    file.write(str(i+90) + " " + str(xSteps[90-i]*-1) + " " +
               str(ySteps[90-i]) + " " + str(dSteps[90-i]) + "\n")

# Special case 180
file.write("180 -.1 0 .1\n")

# reflection over both axes
for i in range(1, 90):
    file.write(str(i+180) + " " + str(xSteps[i]*-1) + " " +
               str(ySteps[i]*-1) + " " + str(dSteps[90-i]) + "\n")

# Special case 270
file.write("270 0 -.1 .1\n")

for i in range(1, 90):
    file.write(str(i+270) + " " + str(xSteps[90-i]) + " " +
               str(ySteps[90-i]*-1) + " " + str(dSteps[90-i]) + "\n")



file.close()

//Author: Scotland Weitzel
//EE 310 Fundamentals of Computer Engineering
//Lab 3: Logic Circuits
//Module: dual_seg7
//Description: This module drives the behavior of two
//             7-segment digital displays. It displays
//             hexadecimal numbers (0-F).

module dual_seg7(input logic [7:0] data, input logic blank, test,
						output logic [6:0] disp1, output logic [6:0] disp2);
						
	seg7 display1(data[3:0], blank, test, disp1);
	seg7 display2(data[7:4], blank, test, disp2);
	
endmodule

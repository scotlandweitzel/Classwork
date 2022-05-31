//Author: 
//EE 310 Fundamentals of Computer Engineering
//Lab 3: Logic Circuits
//Module: seg7
//Description: This module drives the behavior of a
//             7-segment digital display. It displays
//             hexadecimal numbers (0-F).

module seg7(input logic [3:0] data, input logic blank, test,
				output logic [6:0] display);
				
	always_comb
	if (blank) display = 7'b1111111;
	else if (test)  display = 7'b0000000;
	else
		begin
		case (data)
			4'b0000: display = 7'b1000000; //0 good
			4'b0001: display = 7'b1111001; //1 good
			4'b0010: display = 7'b0100100; //2 good
			4'b0011: display = 7'b0110000; //3 good
			4'b0100: display = 7'b0011001; //4 good
			4'b0101: display = 7'b0010010; //5 good
			4'b0110: display = 7'b0000010; //6 good
			4'b0111: display = 7'b1111000; //7 good
			4'b1000: display = 7'b0000000; //8 good
			4'b1001: display = 7'b0010000; //9 good
			4'b1010: display = 7'b0001000; //a good
			4'b1011: display = 7'b0000011; //b good
			4'b1100: display = 7'b1000110; //c 
			4'b1101: display = 7'b0100001; //d
			4'b1110: display = 7'b0000110; //e good
			4'b1111: display = 7'b0001110; //f good
		endcase
		end

endmodule

//Author: Scotland Weitzel
//EE310 Fundamentals of Computer Engineering
//Lab 4: Registers
//Module: accumulator
//Description: This module saves the result from the ALU and
//					outputs the result back to the input of the
// 				ALU.

module accum(input logic [7:0] Z, input logic LOAD_AC,
			     input logic clk, output logic [7:0] AC);

	always_ff @(posedge clk)
		if(LOAD_AC) AC = Z;
		
endmodule

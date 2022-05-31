//Author: Scotland Weitzel
//EE310 Fundamentals of Computer Engineering
//Lab 4: Registers
//Module: Instruction Register
//Description: ...

module instr_reg(input logic [7:0] MDR, input logic LOAD_IRU, LOAD_IRL,
					 input logic reset, clk, output logic [7:0] opcode,
					 output logic [7:0] addr);
					 
			always_ff @(posedge clk, posedge reset)
				if(reset) 
					begin
					opcode = 0;
					addr   = 0;
					end
				else if(LOAD_IRU & ~LOAD_IRL)
					begin
					opcode = MDR;
					end
					
				else if(LOAD_IRL & ~LOAD_IRU)
					begin
					addr = MDR;
					end
					
endmodule

//Author: Scotland Weitzel
//EE310 Fundamentals of Computer Engineering
//Lab 4: Registers
//Name: Program Counter
//Description: ...

module pc(input logic [7:0] addr, 
			 input logic LOAD_PC, INCR_PC, clk, reset,
			 output logic [7:0] PC);

	always_ff @(posedge clk, posedge reset)
		if(reset) PC = 0;
		else if(LOAD_PC)
			begin
			PC = addr;
			end
		else if(INCR_PC && ~LOAD_PC)
			begin
			PC = PC + 1;
			end
			
endmodule

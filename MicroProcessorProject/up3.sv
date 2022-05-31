module up3(input logic clk, reset,
           output logic [7:0] MDR, AC, PC, opcode, value, address,
			  output logic [2:0] STATE,
			  output logic STORE_MEM, FETCH, LOAD_IRU, LOAD_IRL, LOAD_AC, LOAD_PC, INCR_PC);
			  
	// internal buses and signals
	
	logic [7:0] Z;
	logic NFLAG, ZFLAG;

			
	// STRUCTURAL CODE //

	f_mux addr_val_mux(.in0(value), .in1(PC), .sel(FETCH), .out(address));
	alu arithm_unit(.MDR(MDR), .AC(AC), .opcode(opcode), .value(value), .NFLG(NFLAG), .ZFLG(ZFLAG), .Z(Z));
	instr_reg ir(MDR, LOAD_IRU, LOAD_IRL, reset, clk, opcode, value);
	accum accumulator(.Z(Z), .LOAD_AC(LOAD_AC), .AC(AC), .clk(clk));
	pc prog_count(.addr(value), .LOAD_PC(LOAD_PC), .INCR_PC(INCR_PC), .PC(PC), .clk(clk), .reset(reset));
	ramlpm ram_init(.address(address), .data(AC), .clock(clk), .wren(STORE_MEM), .q(MDR));
	

	// CONTROLLER //
	controller controller_init(opcode, NFLAG, ZFLAG, reset, clk, LOAD_AC, LOAD_IRU, LOAD_IRL, LOAD_PC, INCR_PC, FETCH, STORE_MEM, STATE);
	
endmodule

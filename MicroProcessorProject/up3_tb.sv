module up3_tb(input logic [3:0] KEY, output logic [9:0] LEDR,
              output logic [6:0] HEX0, HEX1, HEX2, HEX3, HEX4, HEX5);
	
	// internal buses and signals
	logic[7:0] AC, MDR, PC, opcode, value, address, data0, data1, data2;
	logic[2:0] STATE;
	logic LOAD_AC, LOAD_IRU, LOAD_IRL, LOAD_PC, INCR_PC, STORE_MEM, FETCH;
	
	// up3 processor module
	up3 up3_test(.clk(~KEY[0]), .reset(~KEY[1]), .STORE_MEM(STORE_MEM), .FETCH(FETCH), .LOAD_IRU(LOAD_IRU), .LOAD_IRL(LOAD_IRL), .LOAD_PC(LOAD_PC), 
	             .INCR_PC(INCR_PC), .STATE(STATE), .MDR(MDR), .AC(AC), .PC(PC), .opcode(opcode), .value(value), .address(address));
	
	// board configuration
	always_comb
		if(KEY[3])
			begin
			data2 = PC;
			data1 = address;
			data0 = MDR;
			LEDR[0] = STORE_MEM;
			LEDR[1] = FETCH;
			LEDR[2] = INCR_PC;
			LEDR[3] = LOAD_PC;
			LEDR[4] = LOAD_IRL;
			LEDR[5] = LOAD_IRU;
			LEDR[6] = LOAD_AC;
			LEDR[7] = 0;
			LEDR[8] = 0;
			LEDR[9] = KEY[3];
			end
			
		else
			begin
			data2 = opcode;
			data1 = value;
			data0 = AC;
			LEDR[2:0] = STATE;
			LEDR[8:3] = 0;
			LEDR[9] = KEY[3];
			end
			
	dual_seg7 disp2(.blank(~KEY[2]), .test(~KEY[2]), .data(data2), .disp1(HEX4), .disp2(HEX5));
	dual_seg7 disp1(.blank(~KEY[2]), .test(~KEY[2]), .data(data1), .disp1(HEX2), .disp2(HEX3));
	dual_seg7 disp0(.blank(~KEY[2]), .test(~KEY[2]), .data(data0), .disp1(HEX0), .disp2(HEX1));
	
endmodule

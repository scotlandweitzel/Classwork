module alu(input logic signed[7:0] opcode, 
           input logic signed [7:0] value, MDR, AC,
           output logic NFLG, ZFLG,
			  output logic signed [7:0] Z);
	
	assign NFLG = AC[7]?1:0;
	assign ZFLG = (AC == 0)?1:0;
	
	always_comb
		begin
		
		case (opcode)
			// NO OP
			8'h00: Z = 8'h00;
			
			// LOAD address
			8'h01: Z = MDR;
			
			// LOADI value
			8'h02: Z = value;
					 
         // STORE address			
			8'h03: Z = 8'h00;
			
		   // CLR
			8'h04: Z = 0;
					 
         // ADD address					 
			8'h05: Z = AC + MDR;
					 
		   // ADDI value			 
			8'h06: Z = AC + value;
					 
	      // SUBT address				 
			8'h07: Z = AC - MDR;
					 
	      // SUBTI address				 
			8'h08: Z = AC - value;
					 
	      // NEG address
			8'h09: Z = ~MDR + 8'h01;
					 
			// NOT address
			8'h0A: Z = ~MDR;
					 
		   // AND address
			8'h0B: Z = AC & MDR;
					 
		   // OR address
			8'h0C: Z = AC | MDR;
					 
			// XOR address 
			8'h0D: Z = AC ^ MDR;
					 
			// SHL value
			8'h0E: Z = AC << value[2:0];
					 
			// SHR value
			8'h0F: Z = AC >> value[2:0];
		   
			// LOAD FF on Z if opcode is not valid
			default: Z = 8'h00;
		endcase
		end

				
endmodule

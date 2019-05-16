----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    09:16:17 11/14/2013 
-- Design Name: 
-- Module Name:    IP_Rdm - Rdm 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity IP_Rdm is
			GENERIC (Mycode : std_logic_vector(10 downto 0):= "00000010000" );	
			port (
				clk , reset : in std_logic; 
				IPcode : in std_logic_vector (10 downto 0);
				Tout : out std_logic_vector (31 downto 0)
			);
			
end IP_Rdm;

architecture Rdm of IP_Rdm is

constant Random_size : integer := 32;

signal EN   : std_logic := '0';
signal qbus : std_logic_vector (Random_size - 1 downto 0) ;

	COMPONENT random
	    generic ( width : integer :=  32 ); 
	PORT(
		clk, reset  : IN std_logic;
		enable : IN std_logic;          
		random_num : out std_logic_vector (width-1 downto 0)   --output vector            
		);
	END COMPONENT;



begin

	Inst_random: random 
	Generic map( width => 32)
	PORT MAP(
		clk => clk,
		reset => reset ,
		enable => EN,
		random_num => qbus 
	);

EN <= '1' when IPcode(10 downto 0) = Mycode else '0';
Tout <= qbus  when EN='1' else (others =>'Z');
end Rdm;


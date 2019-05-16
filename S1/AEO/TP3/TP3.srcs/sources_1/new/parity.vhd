----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/03/2018 09:47:56 AM
-- Design Name: 
-- Module Name: parity - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
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
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity parity is
    Port ( a : in STD_LOGIC_VECTOR (15 downto 0);
           res : out STD_LOGIC);
end parity;

architecture Behavioral of parity is

begin

    PROCESS(a)
    VARIABLE nombre_1: STD_LOGIC;
    BEGIN
        nombre_1 := '0';
        FOR I IN 0 TO 15 LOOP
            nombre_1 := nombre_1 XOR a(I);
        END LOOP;
    res <= nombre_1;
    END PROCESS;
end Behavioral;

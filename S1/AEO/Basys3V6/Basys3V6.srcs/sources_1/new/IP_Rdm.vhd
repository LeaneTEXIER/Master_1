----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/21/2018 10:25:19 AM
-- Design Name: 
-- Module Name: IP_Rdm - Behavioral
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

entity IP_rdm is
    GENERIC (Mycode : std_logic_vector(10 downto 0) := "00000010000"
);
    port (
        clk , reset : in std_logic;
        IPcode : in std_logic_vector (10 downto 0);
        Tout : out std_logic_vector (31 downto 0);
        Nout : out std_logic_vector (31 downto 0)
    );
end IP_rdm;


architecture Behavioral of IP_Rdm is

    component random 
        generic ( width : integer :=  32 ); 
        port (
            clk , reset : in std_logic;
            enable : in std_logic;
            random_num : out std_logic_vector (width-1 downto 0)   --output vector            
        );
    end component;

signal enable_s : STD_LOGIC;
signal random_num_s : std_logic_vector (31 downto 0);

begin

cmp : random port map (clk => clk, reset => reset, enable => enable_s, random_num => random_num_s);
enable_s <=  '1' when ipcode(10 downto 0)= Mycode else '0';
-- Tout <= random_num_s when ipcode(10 downto 0)= Mycode else (others =>'Z');
Tout <= "00000000000000000000" & random_num_s(11 downto 0) when ipcode(10 downto 0)= Mycode else (others =>'Z');
Nout <= "00000000000000000000" & random_num_s(23 downto 12) when ipcode(10 downto 0)= Mycode else (others =>'Z');
end Behavioral;
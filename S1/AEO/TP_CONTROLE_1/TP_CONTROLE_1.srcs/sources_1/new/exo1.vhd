library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity exo_1 is
 port(     CLK : in std_logic;
           DI : in std_logic_vector(7 downto 0);
           SEL : in std_logic_vector(1 downto 0);
           SO : out std_logic_vector(7 downto 0));
end exo_1;

architecture archi of exo_1 is
begin
 process (CLK)
 begin
     if (CLK'event and CLK = '1') then
           case sel is
                when "00" => SO <= DI ;
                when "01" => SO <= DI(6 downto 0) &'1';
                when "10" => SO <= DI(5 downto 0) &"11";
                when "11" => SO <= DI(4 downto 0) &"111";
                when others => SO <= DI ;
           end case;
     end if;
 end process;
end archi;
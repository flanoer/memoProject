package TestPack;

import java.math.BigInteger;

public class Test {

	public static void main(String[] args) {
		String test = "8b20b9db3dedde6085cb4034fffd13389e30b4af26e89180792d8972d89b0fcf250b81c19cdcac4b677f2ec8e965a1121bcc4c84457e0e0c231cf1dacde78dcd5b0361f882296b78ce481687637babbee3b0f0cd5d41dd55c1ba82a531fc17600f220573ebf5fc45f0e8dc2f99a1a04638b857e115f9c04257f7653f3ea49ec7fc399801d0ca2139f73474eb0f81e2d6ed33f926f687e56da64b082d0fa4b0d87029c004f155907f084475ede33b6d30ba033f77f7eaa43e5325ec908f94a819e177931a7cc092e9a5847de20bb51ea1fbef2c865f4cb4f14214a7ab54ea25fc9666272091687e0470d107beee0a29661757bcd1faacd67ca95f03d5e5772d69";
		BigInteger bi = new BigInteger(test, 16);
		System.out.println(bi);

	}

}
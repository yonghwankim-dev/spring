package chap05_01.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

import chap05_01.spring.MemberDao;
import chap05_01.spring.MemberPrinter;
import chap05_01.spring.MemberSummaryPrinter;
import chap05_01.spring.VersionPrinter;


// 변경점
// excludeFilters 속성을 사용하면 스캔할 때 특정 대상을 자동 등록 대상에서 제외할 수 있다.
@Configuration
@ComponentScan(basePackages = {"chap05_01.spring", "chap05_02.spring"},
	// 방법1	REGEX을 활용하여 컴포넌트 스캔 대상 제외
	//excludeFilters = @Filter(type = FilterType.REGEX, pattern = "chap05_01.spring\\..*Dao"),
	
	// 방법2	ASPECTJ를 활용하여 컴포넌트 스캔 대상 제외
	//excludeFilters = @Filter(type = FilterType.ASPECTJ, pattern = "chap05_01.spring.*Dao"),
	
	// 방법3 ANNOTATION을 이용하여 컴포넌트 스캔 대상 제외
	excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = {NoProduct.class, ManualBean.class})
)
public class AppCtxWithExclude {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	

	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}

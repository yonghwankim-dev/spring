# 학습키워드

- @MappedSuperClass
- @EntityListener
    - AuditingEntityListener 클래스
- @CreatedDate
- @LastModifiedDate

## @MappedSuperClass
- JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createDate, modifiedDate)도 컬럼으로 인식하도록 합니다.

## @EntityLister(AuditingEntityListener.class)
- BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다.

## @CreatedDate
- Entity가 생성되어 저장될 때 시간이 자동 저장됩니다.

## @LastModifiedDate
- 조회한 Entity의 값을 변경할 때 시간이 자동 저장됩니다.


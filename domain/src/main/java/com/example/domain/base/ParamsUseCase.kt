/**
 * 파라미터 있는 유스케이스 기본 구조 클래스
 * 코드 중복을 최소화하기 위해 작성함, 파라미터로 관련 레포지토리(인터페이스)가 전달됨
 *
 * @author 최승연
 * @date 2021-09-09
 * */
package com.example.domain.base

abstract class ParamsUseCase<in Params, out T> {
    abstract suspend fun buildUseCase(params: Params): T
}
/**
 * 파라미터 없는 유스케이스 기본 구조 클래스
 * 코드 중복을 최소화하기 위해 작성함
 *
 * @author 최승연
 * @date 2021-09-09
 * */
package com.example.domain.base

abstract class BaseUseCase<out T> {
    abstract suspend fun invoke(): T
}
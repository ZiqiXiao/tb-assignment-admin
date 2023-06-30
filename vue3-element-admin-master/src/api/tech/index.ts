import {AxiosPromise} from "axios/index";
import request from "@/utils/request";
import {TechForm, TechPageVO, TechQuery} from "@/api/tech/types";
import {UserForm} from "@/api/user/types";


/**
 * 获取老师分页列表
 *
 * @param queryParams
 */
export function getTechPage(queryParams: TechQuery): AxiosPromise<PageResult<TechPageVO[]>> {
  return request({
    url: '/api/v1/tech/page',
    method: 'get',
    params: queryParams
  });
}

/**
 * 获取老师表单
 *
 * @param techId
 */
export function getTechForm(techId: number): AxiosPromise<TechForm> {
  return request({
    url: '/api/v1/tech/' + techId + '/form',
    method: 'get'
  });
}

/**
 * 修改老师
 *
 * @param techId
 * @param data
 */
export function updateTech(techId: number, data:TechForm) {
  return request({
    url: '/api/v1/tech/' + techId,
    method: 'put',
    data: data
  });
}

/**
 * 添加老师
 *
 * @param data
 */
export function addTech(data: any) {
  return request({
    url: '/api/v1/tech',
    method: 'post',
    data: data
  });
}

/**
 * 获取最大的老师Id
 */
export function getMaxTechId(): AxiosPromise<Number> {
  return request({
    url: '/api/v1/tech/max-tech-id',
    method: 'get'
  });
}
/**
 * 导出老师
 *
 * @param queryParams
 * @returns
 */
export function exportTech(queryParams: TechQuery) {
  return request({
    url: '/api/v1/tech/_export',
    method: 'get',
    params: queryParams,
    responseType: 'arraybuffer'
  });
}

/**
 * 导入老师
 * @param file
 */
export function importTech(file: File) {
  const formData = new FormData();
  formData.append('file', file);
  return request({
    url: '/api/v1/tech/_import',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
}

/**
 * 下载老师导入模板
 *
 * @returns
 */
export function downloadTemplateApi() {
  return request({
    url: '/api/v1/tech/template',
    method: 'get',
    responseType: 'arraybuffer'
  });
}

/**
 * 删除老师
 *
 * @param techIds
 */
export function deleteTechs(techIds: string) {
  return request({
    url: '/api/v1/tech/' + techIds,
    method: 'delete'
  });
}
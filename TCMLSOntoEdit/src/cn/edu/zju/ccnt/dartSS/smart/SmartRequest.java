package cn.edu.zju.ccnt.dartSS.smart;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 定义request类。
 * 定义这个类的目的是：当用组件的方式上传文件时，必须在html的form中采
 * 用post方法通过异步的方式传输文件，同时采用 enctype=\"multipart/form-data\" 
 * 来定义编码的类型，采用这种编码格式时，form中的数据是通过stream的格式来传递的，
 * 因而无法取出form中除了file之外的其他input值。此类的目的就是从stream中取input的值。
 * 因为上传文件时，需要选择专题的名称，专题的名称需要通过一个optional类型的input输入。
 ×@author zhm
 */
public class SmartRequest
{
    private Hashtable m_parameters;
    private int m_counter;

    SmartRequest()
    {
        m_parameters = new Hashtable();
        m_counter = 0;
    }

    /**向requst中存入参数
     * @param s  参数名
     * @param s1 参数值
     */
    protected void putParameter(String s,String s1)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("The name of an element cannot be null.");
        }
        if(m_parameters.containsKey(s))
        {
            Hashtable hashtable = (Hashtable)m_parameters.get(s);
            hashtable.put(new Integer(hashtable.size()),s1);
        }
        else
        {
            Hashtable hashtable1 = new Hashtable();
            hashtable1.put(new Integer(0),s1);
            m_parameters.put(s,hashtable1);
            m_counter++;
        }
    }

    /**取参数值
     * @param s 参数名
     * @return 参数值
     */
    public String getParameter(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
        }
        Hashtable hashtable = (Hashtable)m_parameters.get(s);
        if(hashtable == null)
        {
            return null;
        }
        else
        {
            return(String)hashtable.get(new Integer(0));
        }
    }

    /**取得所有的参数名
     * @return
     */
    public Enumeration getParameterNames()
    {
        return m_parameters.keys();
    }

    /** 取得参数值
     * @param s
     * @return
     */
    public String[] getParameterValues(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
        }
        Hashtable hashtable = (Hashtable)m_parameters.get(s);
        if(hashtable == null)
        {
            return null;
        }
        String as[] = new String[hashtable.size()];
        for(int i = 0;i < hashtable.size();i++)
        {
            as[i] = (String)hashtable.get(new Integer(i));
        }
        return as;
    }
}
